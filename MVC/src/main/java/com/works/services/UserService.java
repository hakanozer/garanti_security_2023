package com.works.services;

import com.works.entities.UserModel;
import com.works.repositoires.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserModelRepository userModelRepository;
    final TinkEncDec tinkEncDec;
    final HttpServletRequest req;

    public boolean singup(UserModel user) {
        boolean status  = singSelectControl(user);
        if ( status ) {
            // insert
            Optional<UserModel> modelOptional = userModelRepository.findByEmailEqualsIgnoreCase(user.getEmail());
            if (modelOptional.isPresent()) {
                return false;
            }else {
                user.setPassword( tinkEncDec.encrypt( user.getPassword() ) );
                userModelRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean singSelectControl(UserModel user) {
        String[] classArr = {"1", "2", "3"};
        for (String item: classArr) {
            if (item.equals(user.getSelectClass())) {
                return true;
            }
        }
        return false;
    }

    //  User Login
    public boolean login( UserModel userModel ) {
        Optional<UserModel> modelOptional = userModelRepository.findByEmailEqualsIgnoreCase(userModel.getEmail());
        if (modelOptional.isPresent()) {
            UserModel model = modelOptional.get();
            String plainPass = tinkEncDec.decrypt( model.getPassword() );
            if ( plainPass.equals( userModel.getPassword() ) ) {
                // Create Session
                req.getSession().setAttribute("user", model);
                return true;
            }
        }
        return false;
    }

}

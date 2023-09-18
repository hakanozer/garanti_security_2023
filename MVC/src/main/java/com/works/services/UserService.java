package com.works.services;

import com.works.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean singup(User user) {
        String[] classArr = {"1", "2", "3"};
        for (String item: classArr) {
            if (item.equals(user.getSelectClass())) {
                return true;
            }
        }
        return false;
    }

}

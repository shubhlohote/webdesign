package com.neu.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.me.pojo.UserAccount;

public class UserValidator  implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(UserAccount.class);
    }

    public void validate(Object obj, Errors errors)
    {
    	System.out.println("inside validator");
        UserAccount userAccount = (UserAccount) obj;
        String usern = userAccount.getUsername();
        String pass = userAccount.getPassword();
        String firstname = userAccount.getPerson().getFirstName();
        String lastName = userAccount.getPerson().getLastName();
        String phone = userAccount.getPerson().getPhone();
        int age = userAccount.getPerson().getAge();
        String email = userAccount.getPerson().getEmail();
        
        String valfirst = firstname.replaceAll("[^\\dA-Za-z] ", "").replaceAll("\\s"," ").trim();
        String vallast = lastName.replaceAll("[^\\dA-Za-z] ", "").replaceAll("\\s"," ").trim();
        String valPhone = phone.replaceAll("[^\\dA-Za-z] ", "").replaceAll("\\s"," ").trim();
        String valemail = email.replaceAll("[^\\dA-Za-z] ", "").replaceAll("\\s"," ").trim();
        userAccount.getPerson().setFirstName(valfirst);
        userAccount.getPerson().setLastName(vallast);
        userAccount.getPerson().setPhone(valPhone);
        userAccount.getPerson().setEmail(valemail);
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.firstName", "error.invalid.password", "Firstname Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.lastName", "error.invalid.password", "Lastname is Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.phone", "error.invalid.password", "Phone is Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.email", "error.invalid.password", "Email is Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.age", "error.invalid.age", "Age is Required");
        
    }
}

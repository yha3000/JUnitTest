package com.example.test03;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })

public @interface ExpectToFail {

}
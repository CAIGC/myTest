package com.cai.modules.DelayQNotify.base;

import java.lang.annotation.*;

/**
 * Created by caigc on 19/5/12.
 */
@Documented
@Inherited
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface topic {
    public String value() default "";
}

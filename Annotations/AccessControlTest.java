package Annotations_Reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface RoleAllowed {
    String value();
}

class RoleBasedAccessControl {
    @RoleAllowed("ADMIN")
    public void adminMethod() {
        System.out.println("Admin method executed");
    }

    @RoleAllowed("USER")
    public void userMethod() {
        System.out.println("User method executed");
    }

    public void commonMethod() {
        System.out.println("Common method executed");
    }
}

public class AccessControlTest {
    public static void main(String[] args) throws Exception {
        RoleBasedAccessControl accessControl = new RoleBasedAccessControl();
        String currentRole = "USER";

        Method[] methods = RoleBasedAccessControl.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(RoleAllowed.class)) {
                RoleAllowed annotation = method.getAnnotation(RoleAllowed.class);
                String allowedRole = annotation.value();

                if (currentRole.equals(allowedRole)) {
                    method.invoke(accessControl);
                } else {
                    System.out.println("Access Denied! You don't have permission to access this method.");
                }
            } else {
                method.invoke(accessControl);
            }
        }
    }
}


package lv.javaguru.courses.ingenico.lecture1.inheritance.anonymous;

public class OverrideUserMethod {

    public static void main(String[] args) {
        User overriddenEmail = new User(2, "email"){
            @Override
            public String getEmail() {
                return "overridden email";
            }
        };
        System.out.println(overriddenEmail);
        System.out.println("getter : " + overriddenEmail.getEmail());
    }

}

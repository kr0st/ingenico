package lv.javaguru.courses.ingenico.lecture8.aspects;

public class Service {

    @Monitored
    public String doSomething(String argument){
        return "done";
    }

}

package ectimel.modules.user_access.controllers;

public class Main {
    
    
    public static void main(String[] args) {

        var a  = new Test2("abca");
        var b = new Test2("abca");

        var aa = new Test(a);
        var bb = new Test(b);
        

        System.out.println(aa.equals(bb));
        
    }
}

record Test(Test2 abc) {}

record Test2(String abc) {}

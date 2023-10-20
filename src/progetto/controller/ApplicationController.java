package progetto.controller;
import progetto.model.domain.Credentials;

public class ApplicationController implements Controller {
    Credentials cred;

    @Override
    public void start() {
        LoginController loginController = new LoginController();
        loginController.start();
        cred = loginController.getCred();

        if(cred.getRole() == null) {
            throw new RuntimeException("Credenziali non valide");
        }

        switch(cred.getRole()) {
            case MANAGER -> new ManagerController().start();
            case OPERATORE -> new OperatoreController().start();
            case MAGAZZINIERE -> new MagazziniereController().start();
            default -> throw new RuntimeException("Credenziali non valide");
        }
    }
}

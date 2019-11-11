package Server;

class ServerProtocol {
    private static final int BEFORE_INIT = 0;
    private static final int WAITING_FOR_REQUEST = 1;

    private int state = BEFORE_INIT;

    Object processInput(String theInput){
        Object theOutput = null;


        if (state == BEFORE_INIT) {
            theOutput = "CONNECTED \nInput: ";
            state = WAITING_FOR_REQUEST;
        }
        return theOutput;
    }
}

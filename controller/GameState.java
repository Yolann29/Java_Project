package controller;

public class GameState {

    private boolean overWorld = true;
    private boolean menu = true;
    public GameState(){

    }

    public void setOverWorld(boolean overWorld){
        this.overWorld = overWorld;
    }

    public boolean getOverWorld(){
        return overWorld;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public boolean getMenu() {
        return menu;
    }
}

package command;

public class VolumeDown implements Command {
    private RemoteControl remoteControl;

    public VolumeDown() {
        this.remoteControl = new RemoteControl();
    }

    @Override
    public void execute() {
        int currentVolume = this.remoteControl.getVolume();
        this.remoteControl.setVolume(currentVolume - 1);
    }
}

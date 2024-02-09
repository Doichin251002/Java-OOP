package command;

public class VolumeUp implements Command {
    private RemoteControl remoteControl;

    public VolumeUp() {
        this.remoteControl = new RemoteControl();
    }

    @Override
    public void execute() {
        int currentVolume = this.remoteControl.getVolume();
        this.remoteControl.setVolume(currentVolume + 1);
    }
}


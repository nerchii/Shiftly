import java.util.EventListener;

@FunctionalInterface
public interface FormPanelListener extends EventListener {
    void formPanelEventOccurred(Worker worker);
}

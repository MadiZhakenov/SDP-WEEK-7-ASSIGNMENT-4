import java.util.ArrayList;
import java.util.List;

// Task 1
interface Handler {
    void setNext(Handler handler);
    void handleRequest(String request);
}
class ConcreteHandler1 implements Handler {
    private Handler next;
    public void setNext(Handler handler) {
        next = handler;
    }
    public void handleRequest(String request) {
        if (request.equals("Task1")) {
            System.out.println("Handled by Handler1");
        } else if (next != null) {
            System.out.println("Handler1 passing the request to next handler");
            next.handleRequest(request);
        } else {
            System.out.println("Request cannot be handled");
        }
    }
}
class ConcreteHandler2 implements Handler {
    private Handler next;
    public void setNext(Handler handler) {
        next = handler;
    }
    public void handleRequest(String request) {
        if (request.equals("Task2")) {
            System.out.println("Handled by Handler2");
        } else if (next != null) {
            System.out.println("Handler2 passing the request to next handler");
            next.handleRequest(request);
        } else {
            System.out.println("Request cannot be handled");
        }
    }
}
class ConcreteHandler3 implements Handler {
    private Handler next;
    public void setNext(Handler handler) {
        next = handler;
    }
    public void handleRequest(String request) {
        if (request.equals("Task3")) {
            System.out.println("Handled by Handler3");
        } else if (next != null) {
            System.out.println("Handler3 passing the request to next handler");
            next.handleRequest(request);
        } else {
            System.out.println("Request cannot be handled");
        }
    }
}

// Task 2
interface Command {
    void execute();
}
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }
    public void turnOff() {
        System.out.println("Light is OFF");
    }
}
class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnOn();
    }
}
class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnOff();
    }
}
class RemoteControl {
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public void pressButton() {
        command.execute();
    }
}

// Task 3
interface Expression {
    boolean interpret(String context);
}
class TerminalExpression implements Expression {
    private String data;
    public TerminalExpression(String data) {
        this.data = data;
    }
    public boolean interpret(String context) {
        return context.contains(data);
    }
}
class OrExpression implements Expression {
    private Expression expr1;
    private Expression expr2;
    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}
class AndExpression implements Expression {
    private Expression expr1;
    private Expression expr2;
    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    public boolean interpret(String context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}

// Task 4
interface Iterator {
    boolean hasNext();
    Object next();
}
class NameRepository {
    public String names[] = {"John", "Jane", "Alice", "Bob"};
    public Iterator getIterator() {
        return new NameIterator();
    }
    private class NameIterator implements Iterator {
        int index;

        public boolean hasNext() {
            return index < names.length;
        }

        public Object next() {
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}

// Task 5
interface Mediator {
    void sendMessage(String message, Colleague colleague);
}
class ConcreteMediator implements Mediator {
    private Colleague colleague1;
    private Colleague colleague2;
    public void setColleague1(Colleague colleague) {
        colleague1 = colleague;
    }
    public void setColleague2(Colleague colleague) {
        colleague2 = colleague;
    }
    public void sendMessage(String message, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.receive(message);
        } else {
            colleague1.receive(message);
        }
    }
}
abstract class Colleague {
    protected Mediator mediator;
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
    public abstract void send(String message);
    public abstract void receive(String message);
}
class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }
    public void send(String message) {
        System.out.println("Colleague1 sends message: " + message);
        mediator.sendMessage(message, this);
    }
    public void receive(String message) {
        System.out.println("Colleague1 receives message: " + message);
    }
}
class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }
    public void send(String message) {
        System.out.println("Colleague2 sends message: " + message);
        mediator.sendMessage(message, this);
    }
    public void receive(String message) {
        System.out.println("Colleague2 receives message: " + message);
    }
}

// Task 6
class Memento {
    private String state;
    public Memento(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
}
class Originator {
    private String state;
    public void setState(String state) {
        System.out.println("Setting state to " + state);
        this.state = state;
    }
    public String getState() {
        return state;
    }
    public Memento saveStateToMemento() {
        System.out.println("Saving state to memento: " + state);
        return new Memento(state);
    }
    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
        System.out.println("Restored state from memento: " + state);
    }
}
class Caretaker {
    private List<Memento> mementoList = new ArrayList<>();
    public void add(Memento state) {
        mementoList.add(state);
    }
    public Memento get(int index) {
        return mementoList.get(index);
    }
}

public class Main {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        System.out.println("Sending Task1:");
        handler1.handleRequest("Task1");
        System.out.println("\nSending Task2:");
        handler1.handleRequest("Task2");
        System.out.println("\nSending Task3:");
        handler1.handleRequest("Task3");
        System.out.println("\nSending Task4 (Unrecognized Task):");
        handler1.handleRequest("Task4");

        Light livingRoomLight = new Light();
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        RemoteControl remote = new RemoteControl();
        // Turn the light ON
        remote.setCommand(lightOn);
        remote.pressButton();
        // Turn the light OFF
        remote.setCommand(lightOff);
        remote.pressButton();

        // Create terminal expressions
        Expression isJava = new TerminalExpression("Java");
        Expression isPython = new TerminalExpression("Python");
        // Create or expression
        Expression isJavaOrPython = new OrExpression(isJava, isPython);
        // Create and expression
        Expression isJavaAndPython = new AndExpression(isJava, isPython);
        System.out.println("Does context contain Java or Python?");
        System.out.println(isJavaOrPython.interpret("I love Java")); // true
        System.out.println("Does context contain both Java and Python?");
        System.out.println(isJavaAndPython.interpret("I love Java and Python")); // true

        NameRepository nameRepository = new NameRepository();
        Iterator iterator = nameRepository.getIterator();
        System.out.println("Iterating through names:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        ConcreteMediator mediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleague1(mediator);
        Colleague colleague2 = new ConcreteColleague2(mediator);
        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);
        colleague1.send("Hello, Colleague2!");
        colleague2.send("Hi, Colleague1!");

        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("State #1");
        originator.setState("State #2");
        caretaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        caretaker.add(originator.saveStateToMemento());
        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("Restored to State #1: " + originator.getState());
        originator.getStateFromMemento(caretaker.get(1));
        System.out.println("Restored to State #2: " + originator.getState());
    }
}

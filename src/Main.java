import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Message {
    private String author;
    private LocalDateTime timestamp;
    private String content;

    public Message(String author, LocalDateTime timestamp, String content) {
        this.author = author;
        this.timestamp = timestamp;
        this.content = content;
    }

    public String getAuthor() { 
        return author; 
    }
    public LocalDateTime getTimestamp() { 
        return timestamp; 
    }
    public String getContent() { 
        return content; 
    }
}

class Feed {
    private List<Message> messages;

    public Feed() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages);
    }
}

class MessageFilter {
    public List<Message> filterByAuthor(List<Message> messages, String author) {
        return messages.stream().filter(msg -> msg.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }
}

class MessageDisplay {
    public void showMessages(List<Message> messages) {
        messages.stream().sorted((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp())).forEach(msg -> System.out.println("[" + msg.getTimestamp() + "] " + msg.getAuthor() + ": " + msg.getContent()));
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Message postMessage(String content) {
        return new Message(name, LocalDateTime.now(), content);
    }
}

class Main {
    public static void main(String[] args) {
        Feed feed = new Feed();
        MessageFilter filter = new MessageFilter();
        MessageDisplay display = new MessageDisplay();

        User Alice = new User("Alice");
        User Bob = new User("Bob");

        feed.addMessage(Alice.postMessage("Hello!"));
        feed.addMessage(Bob.postMessage("Hey there!"));
        feed.addMessage(Alice.postMessage("Another post."));

        List<Message> allMessages = feed.getAllMessages();
        display.showMessages(allMessages);
    }
}



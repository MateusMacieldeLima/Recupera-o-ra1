package Recuperação.Ra1;

// Classe Node: Representa um nó da lista duplamente encadeada
class Node {
    String address;
    Node prev;
    Node next;
    
    public Node(String address) {
        this.address = address;
        this.prev = null;
        this.next = null;
    }
}

// Classe DoublyLinkedList: Implementa uma lista duplamente encadeada para o histórico de navegação
class DoublyLinkedList {
    private Node head;
    private Node tail;

    // Construtor: Inicializa uma lista vazia
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Método insert: Insere um novo endereço no final do histórico
    public void insert(String address) {
        Node newNode = new Node(address);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Método remove: Remove um endereço específico do histórico
    public boolean remove(String address) {
        Node current = head;
        while (current != null) {
            if (current.address.equals(address)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Método listAll: Exibe todos os endereços no histórico
    public void listAll() {
        Node current = head;
        while (current != null) {
            System.out.println(current.address);
            current = current.next;
        }
    }
}

// Classe BrowserHistory: Classe principal para testar o histórico de navegação
public class BrowserHistory {
    public static void main(String[] args) {
        DoublyLinkedList history = new DoublyLinkedList();

        String[] initialAddresses = {
            "www.google.com",
            "www.facebook.com",
            "www.youtube.com",
            "www.twitter.com",
            "www.instagram.com",
            "www.chatgpt.com",
            "www.github.com",
            "www.stackoverflow.com",
            "www.reddit.com",
            "www.wikipedia.org"
        };

        for (String address : initialAddresses) {
            history.insert(address);
        }

        System.out.println("Histórico inicial:");
        history.listAll();

        System.out.println("\nAdicionando nova página: www.example.com");
        history.insert("www.example.com");
        history.listAll();

        System.out.println("\nRemovendo página: www.facebook.com");
        if (history.remove("www.facebook.com")) {
            System.out.println("Página removida com sucesso.");
        } else {
            System.out.println("Página não encontrada.");
        }

        history.listAll();
    }
}

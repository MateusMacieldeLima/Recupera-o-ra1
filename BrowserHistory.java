package Recuperação.Ra1;

// Classe Node: Representa um nó da lista duplamente encadeada
class Node {
    String address; // Endereço da página
    Node prev; // Referência para o nó anterior
    Node next; // Referência para o próximo nó
    
    public Node(String address) {
        this.address = address;
        this.prev = null;
        this.next = null;
    }
}

// Classe DoublyLinkedList: Implementa uma lista duplamente encadeada para o histórico de navegação
class DoublyLinkedList {
    private Node head; // Referência para o primeiro nó
    private Node tail; // Referência para o último nó

    // Construtor: Inicializa uma lista vazia
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Método insert: Insere um novo endereço no final do histórico
    public void insert(String address) {
        Node newNode = new Node(address);
        if (head == null) { // Lista vazia
            head = tail = newNode;
        } else { // Lista já contém elementos
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Método remove: Remove um endereço específico do histórico
    public boolean remove(String address) {
        Node current = head;
        while (current != null) { // Percorre a lista para encontrar o nó
            if (current.address.equals(address)) { // Endereço encontrado
                if (current.prev != null) { // Atualiza o próximo do anterior
                    current.prev.next = current.next;
                } else { // Caso seja o primeiro nó
                    head = current.next;
                }
                if (current.next != null) { // Atualiza o anterior do próximo
                    current.next.prev = current.prev;
                } else { // Caso seja o último nó
                    tail = current.prev;
                }
                return true; // Sucesso na remoção
            }
            current = current.next; // Avança para o próximo nó
        }
        return false; // Endereço não encontrado
    }

    // Método listAll: Exibe todos os endereços no histórico
    public void listAll() {
        Node current = head;
        while (current != null) { // Percorre a lista e exibe os endereços
            System.out.println(current.address);
            current = current.next;
        }
    }
}

// Classe BrowserHistory: Classe principal para testar o histórico de navegação
public class BrowserHistory {
    public static void main(String[] args) {
        DoublyLinkedList history = new DoublyLinkedList(); // Cria uma lista duplamente encadeada para o histórico

        // Endereços iniciais para popular o histórico
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

        // Adiciona os endereços iniciais ao histórico
        for (String address : initialAddresses) {
            history.insert(address);
        }

        // Exibe o histórico inicial
        System.out.println("Histórico inicial:");
        history.listAll();

        // Adiciona uma nova página ao histórico
        System.out.println("\nAdicionando nova página: www.example.com");
        history.insert("www.example.com");
        history.listAll();

        // Remove uma página específica do histórico
        System.out.println("\nRemovendo página: www.facebook.com");
        if (history.remove("www.facebook.com")) {
            System.out.println("Página removida com sucesso.");
        } else {
            System.out.println("Página não encontrada.");
        }

        // Exibe o histórico atualizado
        history.listAll();
    }
}

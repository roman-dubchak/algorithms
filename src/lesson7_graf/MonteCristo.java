package lesson7_graf;


import lesson3_stack.Queue;
import lesson3_stack.Stack;

public class MonteCristo {

    private static class Graph {
        private class Vertex {
            char label;
            Vertex parent;
            boolean wasVisited;

            public Vertex(char label) {
                this.label = label;
                this.wasVisited = false;
            }

            @Override
            public String toString() {
                return String.format("V=%c", label);
            }
        }

        private final int MAX_VERTICES;
        private Vertex[] vertexList;
        private int[][] adjacencyMatrix;
        private int currentSize;

        public Graph(int maxVertices) {
            MAX_VERTICES = maxVertices;
            vertexList = new Vertex[MAX_VERTICES];
            adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
            this.currentSize = 0;
        }
        public void addVertex(char label) {
            vertexList[currentSize++] = new Vertex(label);
        }
        public void addEdge(int start, int end) {
            adjacencyMatrix[start][end] = 1; // change 1 to weight for weight
            adjacencyMatrix[end][start] = 1; // delete this for direction
        }
        public void displayVertex(int v) {
            System.out.print(vertexList[v] + " ");
        }
        private int getUnvisitedVertex(int current) {
            for (int i = 0; i < currentSize; i++) {
                if (adjacencyMatrix[current][i] == 1 &&
                        !vertexList[i].wasVisited) {
                    return i;
                }
            }
            return -1;
        }

        public void depthTraverse() {
            Stack stack = new Stack(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            displayVertex(0);
            stack.push(0);
            while (!stack.isEmpty()) {
                int v = getUnvisitedVertex(stack.peek());
                if (v == -1) {
                    stack.pop();
                } else {
                    vertexList[v].wasVisited = true;
                    displayVertex(v);
                    stack.push(v);
                }
            }
            resetFlags();
        }
        public void widthTraverse() {
            Queue queue = new Queue(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            displayVertex(0);
            queue.insert(0);
            while (!queue.isEmpty()) {
                int current = queue.remove();
                displayVertex(current);
                int next;
                while ((next = getUnvisitedVertex(current)) != -1) {
                    vertexList[next].wasVisited = true;
                    displayVertex(next);
                    queue.insert(next);
                }
            }
            resetFlags();
        }
        private void resetFlags() {
            for (int i = 0; i < currentSize; i++) {
                vertexList[i].wasVisited = false;
            }
        }

        public Queue widthTraversPath(char from, char to) {
            int start = getIndex(from);
            int stop = getIndex(to);

            Queue queue = new Queue(MAX_VERTICES);
            vertexList[start].wasVisited = true;
            queue.insert(start);
            boolean done = false;
            while (!queue.isEmpty()) {
                int v1 = queue.remove();
                int v2;
                while ((v2 = getUnvisitedVertex(v1)) != -1) {
                    vertexList[v2].wasVisited = true;

                    if (v2 == stop) {
                        done = true;
                        break;
                    }
                    queue.insert(v2);
                }
            }
            resetFlags();
            if (done)
                return queue;
            else
                return null;
        }

        private int getIndex(char c) {
            for (int i = 0; i < vertexList.length; i++) {
                if (vertexList[i].label == c)
                    return i;
            }
            return -1;
        }

        Stack shortWay(char from, char to) {            //граф не взвешен
            Stack result = new Stack(MAX_VERTICES);
            Queue queue = new Queue(MAX_VERTICES);

            int start = getIndex(from);
            int stop = getIndex(to);
            if (start == -1 || stop == -1 || start == stop)
                return null;

            vertexList[start].wasVisited = true;
            queue.insert(start);
            while (!queue.isEmpty()) {                  // ищем узел, помечаем родителей
                int vCur = queue.remove();
                int vNxt;
                while ((vNxt = getUnvisitedVertex(vCur)) != -1) {
                    vertexList[vNxt].parent = vertexList[vCur];
                    vertexList[vNxt].wasVisited = true;
                    if (vNxt == stop) break;
                    queue.insert(vNxt);
                }
                if (vNxt == stop) break;
            }
            if (!vertexList[stop].wasVisited) return null;

            result.push(vertexList[stop].label);
            int current = stop;
            while (vertexList[current].parent != null)        // идём обратно к старту по родителям
                for (int i = 0; i < vertexList.length; i++)
                    if(vertexList[current].parent == vertexList[i]) {
                        result.push(vertexList[i].label);
                        current = i;
                        break;
                    }

            for (int i = 0; i < currentSize; i++) {
                vertexList[i].wasVisited = false;
                vertexList[i].parent = null;
            }
            return result;
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(16);
        g.addVertex('a');
        g.addVertex('b');
        g.addVertex('c');
        g.addVertex('d');
        g.addVertex('e');
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(3, 4);
        g.addEdge(0, 4);
        g.depthTraverse();
        System.out.println();
        g.widthTraverse();
    }
}

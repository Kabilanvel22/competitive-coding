//{ Driver Code Starts
    import java.io.*;
    import java.math.*;
    import java.util.*;
    
    class Node {
        int data;
        Node left, right;
    
        public Node(int d) {
            data = d;
            left = right = null;
        }
    }
    
    public class GFG {
        static Node buildTree(String str) {
            // Corner Case
            if (str.length() == 0 || str.equals('N')) return null;
            String[] s = str.split(" ");
    
            Node root = new Node(Integer.parseInt(s[0]));
            Queue<Node> q = new LinkedList<Node>();
            q.add(root);
    
            // Starting from the second element
            int i = 1;
            while (!q.isEmpty() && i < s.length) {
                // Get and remove the front of the queue
                Node currNode = q.remove();
    
                // Get the curr node's value from the string
                String currVal = s[i];
    
                // If the left child is not null
                if (!currVal.equals("N")) {
    
                    // Create the left child for the curr node
                    currNode.left = new Node(Integer.parseInt(currVal));
    
                    // Push it to the queue
                    q.add(currNode.left);
                }
    
                // For the right child
                i++;
                if (i >= s.length) break;
                currVal = s[i];
    
                // If the right child is not null
                if (!currVal.equals("N")) {
    
                    // Create the right child for the curr node
                    currNode.right = new Node(Integer.parseInt(currVal));
    
                    // Push it to the queue
                    q.add(currNode.right);
                }
    
                i++;
            }
    
            return root;
        }
    
        public static void main(String args[]) throws IOException {
    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine().trim());
            while (t > 0) {
                String s = br.readLine().trim();
                Node root = buildTree(s);
    
                String[] snums = br.readLine().split(" ");
                int k = Integer.parseInt(snums[0]);
                int x = Integer.parseInt(snums[1]);
                int y = Integer.parseInt(snums[2]);
    
                Solution T = new Solution();
                // Call the function with the needed parameters
                System.out.println(T.kthCommonAncestor(root, k, x, y));
    
                t--;
            }
        }
    }
    
    // } Driver Code Ends
    
    
    // User function Template for Java
    class Solution {
        private List<Node> pathX;
        private List<Node> pathY;
    
        public int kthCommonAncestor(Node root, int k, int x, int y) {
            pathX = new ArrayList<>();
            pathY = new ArrayList<>();
            
            if (!findPath(root, x, pathX) || !findPath(root, y, pathY)) {
                return -1; 
            }
            
            int commonAncestorIndex = 0;
            while (commonAncestorIndex < Math.min(pathX.size(), pathY.size()) && pathX.get(commonAncestorIndex) == pathY.get(commonAncestorIndex)) {
                commonAncestorIndex++;
            }
            
            if (commonAncestorIndex < k) {
                return -1; 
            }
            
            return pathX.get(commonAncestorIndex - k).data;
        }
    
        private boolean findPath(Node root, int target, List<Node> path) {
            if (root == null) {
                return false;
            }
    
            path.add(root);
    
            if (root.data == target) {
                return true;
            }
    
            if (findPath(root.left, target, path) || findPath(root.right, target, path)) {
                return true;
            }
    
            path.remove(path.size() - 1);
            return false;
        }
    }
    
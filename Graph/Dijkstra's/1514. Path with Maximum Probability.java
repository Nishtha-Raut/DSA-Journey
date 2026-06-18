// leetcode link: https://leetcode.com/problems/path-with-maximum-probability/
class Node{
    int v;
    double prob;
    Node(int v,double prob){
        this.v=v;
        this.prob=prob;
    }
}
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        HashMap<Integer,ArrayList<Node>>graph=createGraph(edges,succProb);
        double distance[]=new double[n];
        Arrays.fill(distance,0.0);
        distance[start_node]=1.0;
        PriorityQueue<Node>pq=new PriorityQueue<>((a,b)->Double.compare(b.prob,a.prob));
        pq.add(new Node(start_node,1.0));
        while(pq.size()>0){
            Node curr=pq.poll();
            int u=curr.v;
            double prob=curr.prob;
            if(distance[u]>prob) continue;
            if(!graph.containsKey(curr.v)) continue;
            ArrayList<Node>neighbours=graph.get(curr.v);
            for(Node neighbour:neighbours){
                int v=neighbour.v;
                double newprob=prob*neighbour.prob;
                if(newprob>distance[v]){
                    distance[v]=newprob;
                    pq.add(new Node(v,newprob));
                }
            }
        }
        return distance[end_node];
    }
    public HashMap<Integer,ArrayList<Node>> createGraph(int edges[][], double[] succProb){
        HashMap<Integer,ArrayList<Node>> map=new HashMap<>();
        int i=0;
        for(int edge[]:edges){
            int u=edge[0];
            int v=edge[1];
            double prob=succProb[i++];
            if(!map.containsKey(u)){
                map.put(u,new ArrayList<>());
            }
            if(!map.containsKey(v)){
                map.put(v,new ArrayList<>());
            }
            map.get(u).add(new Node(v,prob));
            map.get(v).add(new Node(u,prob));
        }
        return map;
    }
}
"""
Complexity Summary
-> Time Complexity: (O(E log V))
   Building the graph takes O(E) time.
   Dijkstra's traversal processes up to E edges, with each heap operation taking O(log V) time.
-> Space Complexity: O(V + E)
  The adjacency list map requires O(V + E) space to store all nodes and bidirectional edges. 
  The distance array and priority queue take O(V) and O(E) space, respectively.
"""

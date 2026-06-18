//leetcode link: https://leetcode.com/problems/network-delay-time/
class Node{
    int n;
    int w;
    Node(int n,int w){
        this.n=n;
        this.w=w;
    }
}
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer,ArrayList<Node>>map=createGraph(times);
        int distance[]=new int[n];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[k-1]=0;
        PriorityQueue<Node>pq=new PriorityQueue<>((a,b)->a.w-b.w);
        pq.add(new Node(k-1,0));
        while(pq.size()>0){
            Node curr=pq.poll();
            int u=curr.n;
            int w=curr.w;
            if(w>distance[u]) continue;
            if(map.containsKey(u)==false) continue;
            ArrayList<Node>neighbours=map.get(u);
            for(Node neigh:neighbours){
                int v=neigh.n;
                int weight=neigh.w+w;
                if(weight<distance[v]){
                    distance[v]=weight;
                    pq.add(new Node(v,weight));
                }
            }
        }
        int time=-1;
        for(int i=0;i<n;i++){
            if(i!=k-1){
                time=Math.max(time,distance[i]);
            }
        }
        return (time==Integer.MAX_VALUE)?-1:time;
    }
    public HashMap<Integer,ArrayList<Node>> createGraph(int times[][]){
        HashMap<Integer,ArrayList<Node>>map=new HashMap<>();
        for(int time[]:times){
            int u=time[0]-1;
            int v=time[1]-1;
            int w=time[2];
            if(!map.containsKey(u)){
                map.put(u,new ArrayList<>());
            }
            map.get(u).add(new Node(v,w));
        }
        return map;
    }
}
"""
⏱️ Time Complexity 
  (O(E log V))
  Why: Each edge (E) is processed, and updating the Priority Queue takes logarithmic time relative to the number of vertices (V).
💾 Space Complexity
  (O(V + E))
  Why: The adjacency list stores all vertices (V) and edges (E), while the distance array and Priority Queue store up to (V) elements.
"""




  

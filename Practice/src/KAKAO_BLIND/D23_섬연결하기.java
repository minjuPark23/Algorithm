package KAKAO_BLIND;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class D23_섬연결하기 {

    public static void main(String[] args) {
        Assertions.assertEquals(4, solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
    }

    private static int solution(int n, int[][] costs) {
        int answer = 0;
        int[][] map = new int[n][n];
        int[] minEdge = new int[n];
        for (int i = 0; i < costs.length; i++) {
            int[] cost = costs[i];
            map[cost[0]][cost[1]] = cost[2];
        }
        for (int i = 0; i < n; i++) {
            minEdge[i] = Integer.MAX_VALUE;
        }

        boolean[] visited = new boolean[n];
        minEdge[0] = 0; // 0부터 시작

        for (int i = 0; i < n; i++) {
            // 신장트리에 포함되지 않은 녀석들 중 최소 간선 비용 찾기
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && min > minEdge[j]){
                    min = minEdge[j];
                    minIdx = j;
                }
            }

            visited[minIdx] = true;
            answer += min;

            // 선택된 정점 기준으로 신장 트리에 연결되지 않은 간선비용 업데이트
            for(int j = 0; j < n; j++){
                if(!visited[j] && map[minIdx][j] != 0 && minEdge[j] > map[minIdx][j])
                    minEdge[j] = map[minIdx][j];
            }
        }
        return answer;
    }

//    static int[] parents;
//
//    static int find(int a){
//        if (parents[a] == a) return a;
//        return find(parents[a]);
//    }
//
//    static boolean union(int a, int b){
//        int aRoot = find(a);
//        int bRoot = find(b);
//        if (aRoot == bRoot) return false;
//        parents[bRoot] = aRoot;
//        return true;
//    }
//    private static int solution(int n, int[][] costs) {
//        int answer = 0;
//        parents = new int[n];
//        for (int i = 0; i < n; i++) {
//            parents[i] = i;
//        }
//        PriorityQueue<Bridge> q = new PriorityQueue<>();
//        for (int[] cost : costs) {
//            int s = cost[0];
//            int e = cost[1];
//            int c = cost[2];
//            q.offer(new Bridge(s, e, c));
//        }
//
//        while(!q.isEmpty()){
//            Bridge cur = q.poll();
//            if(union(cur.s, cur.e)){
//                answer += cur.cost;
//            }
//        }
//        return answer;
//    }
//
//    static class Bridge implements Comparable<Bridge>{
//        int s;
//        int e;
//        int cost;
//
//        public Bridge(int s, int e, int cost){
//            this.s = s;
//            this.e = e;
//            this.cost = cost;
//        }
//
//        @Override
//        public int compareTo(Bridge b){
//            return Integer.compare(this.cost, b.cost);
//        }
//    }
}

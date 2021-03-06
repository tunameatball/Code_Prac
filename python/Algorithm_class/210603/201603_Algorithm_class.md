알고리즘의 이해
==============

2021 - 06 - 03
--------------


## 그래프


### 깊이 우선 탐색(DFS, Depth First Search)
- 갈림길이 나오면 작은 쪽 정점부터 탐색

### 너비 우선 탐색(BFS, Breadth First Search)
-     


## 신장트리와 최소 비용 신장트리
> 신장트리 
>   - n개의 정점으로 이루어진 무방향 그래프에서 n개의 모든 정점과 n-1개의 간선으로 만들어진 트리
>   - 그래프의 관점에서 트리는 사이클이 없는 단순 연결 그래프

> 최소 비용 신장 트리
>   - 무방향 가중치 그래프에서 신장트리를 구성하는 간선들의 가중치 합이 최소인 신장 트리
>       - 가중치 그래프의 간선에 주어진 가중치
>           * 비용이나 거리, 시간을 의미하는 값
>   - 최소 비용 신장 트리를 만드는 알고리즘
>       * Kruskal 알고리즘
>       * Prime 알고리즘

Kruskal 알고리즘
- 가중치가 높은 간선을 제거하면서 최소 비용 신장 트리를 만드는 방법 (간선이 n-1개가 될 때 까지)
- 가중치 정렬을 하기 때문에 Prime 알고리즘에 비해 작업이 더 많다
    > Kruskal 알고리즘 I
    > 1. 그래프 G의 모든 간선을 가중치에 따라 내림차순으로 정리한다.
    > 2. 그래프 G에서 가중치가 가장 높은 간선을 제거한다.
    >    - 이때 정점을 그래프에서 분리시키는 간선은 제거할 수 없다. 이런 경우에는 그 다음으로 가중치가 높은 간선을 제거한다.
    > 3. 그래프 G에 n-1개의 간선만 남을 때까지 (2)를 반복한다.
    > 4. 그래프에 n01개의 간선이 남게 되면 최소 비용 신장 트리가 완성된다.

    >Kruskal 알고리즘 II
    > 1. 그래프 G의 모든 간선을 가중치에 따라 오름차순으로 정리한다.
    > 2. 그래프 G에 가중치가 가장 작은 간선을 삽입한다. 이때 사이클을 형성하는 간선은 삽입할 수 없다. 이런 경우에는 그 다음으로 가중치가 작은 간선을 삽입한다.
    > 3. 그래프 G에 n-1개의 간선을 삽입할 때까지 (2)를 반복한다.
    > 4. 그래프 G의 간선이 n-1개가 되면 최소 비용 신장 트리가 완성된다.

Prime 알고리즘
- 간선을 정렬하지 않고 하나의 정점에서 시작하여 트리를 확장해 나가는 방법
    > Prime 알고리즘
    > 1. 그래프 G에서 시작 정점을 선택한다.
    > 2. 선택한 정점에 부속된 모든 간선 중에서 가중치가 가장 작은 간선을 연결하여 트리를 확장한다.
    > 3. 이전에 선택한 정점과 새로 확장한 정점에 부속된 모든 간선 중에서 가중치가 가장 작은 간선을 삽입한다. 이때 사이클을 형성하는 간선은 삽입할 수 없다. 이런 경우에는 그 다음으로 가중치가 작은 간선을 선택하여 삽입한다.
    > 4. 그래프 G에 n-1개의 간선을 삽입할 때까지 (3)을 반복한다.
    > 5. 그래프 G의 간선이 n-1개가 되면 최소 비용 신장 트리가 완성된다.



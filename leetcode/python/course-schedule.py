
class Solution:
    def canFinish(self, num_courses, prerequisites):
        graph = {i: [] for i in range(num_courses)}

        for to_take, need_to in prerequisites:
            graph[to_take].append(need_to)
            
        visited = [False] * num_courses
        visited_stack = [False] * num_courses
        
        def dfs(node):
            visited[node] = True
            visited_stack[node] = True
            
            for child in graph[node]:
                if visited_stack[child]: return False
                if not visited[child]:
                    if not dfs(child):
                        return False
            visited_stack[node] = False
            return True
            
        for node in graph:
            if not visited[node]:
                if not dfs(node):
                    return False
                
        return True

assert Solution().canFinish(1, []) == True
assert Solution().canFinish(2, [[0,1]]) == True
assert Solution().canFinish(2, [[1,0]]) == True
assert Solution().canFinish(2, [[1,0],[0,1]]) == False
assert Solution().canFinish(3, [[0,1],[0,2],[1,2]]) == True

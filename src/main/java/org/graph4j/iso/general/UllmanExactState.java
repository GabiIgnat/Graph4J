package org.graph4j.iso.general;

import org.graph4j.Digraph;

import java.util.List;

public class UllmanExactState extends AbstractUllmanState {
    public UllmanExactState(Digraph g1, Digraph g2) {
        super(g1, g2);
    }

    public UllmanExactState(UllmanExactState s) {
        super(s);
    }
    @Override
    public boolean exactOrSubgraphIsomorphismCompatibilityCheck(int v1, int v2) {
        return g1.indegree(v1) == g2.indegree(v2) &&
                g1.outdegree(v1) == g2.outdegree(v2);
    }

    @Override
    public boolean isGoal() {
        return core_len == n1 && core_len == n2;
    }

    @Override
    public boolean isDead() {
        if (n1 != n2)
            return true;

        for (int i = 0; i < n1; i++){
            // for each vertex in g1, there is at least one candidate in g2
            List<Integer> candidates = getCandidates(i);
            if (candidates.isEmpty())
                return true;
        }
        return false;
    }
}
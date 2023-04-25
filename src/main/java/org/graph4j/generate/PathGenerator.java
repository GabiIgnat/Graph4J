/*
 * Copyright (C) 2022 Cristian Frăsinaru and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graph4j.generate;

import org.graph4j.Digraph;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

/**
 *
 * @author Cristian Frăsinaru
 */
public class PathGenerator extends AbstractGraphGenerator {

    public PathGenerator(int numVertices) {
        super(numVertices);
    }

    public PathGenerator(int firstVertex, int lastVertex) {
        super(firstVertex, lastVertex);
    }

    /**
     *
     * @return a path graph.
     */
    public Graph createGraph() {
        int n = vertices.length;
        var g = GraphBuilder.vertices(vertices).estimatedAvgDegree(2)
                .named("P" + n).buildGraph();
        addEdges(g, true);
        return g;
    }

    /**
     *
     * @param leftToRight the orientation of the path.
     * @return a directed path graph.
     */
    public Digraph createDigraph(boolean leftToRight) {
        var g = GraphBuilder.vertices(vertices).estimatedAvgDegree(1).buildDigraph();
        addEdges(g, leftToRight);
        return g;
    }

    private void addEdges(Graph g, boolean leftToRight) {
        boolean safeMode = g.isSafeMode();
        g.setSafeMode(false);
        for (int i = 0, n = vertices.length; i < n - 1; i++) {
            int v = vertices[i];
            int u = vertices[i + 1];
            if (leftToRight) {
                g.addEdge(v, u);
            } else {
                g.addEdge(u, v);
            }
        }
        g.setSafeMode(safeMode);
    }

}

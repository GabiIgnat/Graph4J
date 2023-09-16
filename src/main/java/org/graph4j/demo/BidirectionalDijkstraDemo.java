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
package org.graph4j.demo;

import org.graph4j.generate.EdgeWeightsGenerator;
import org.graph4j.generate.GraphGenerator;
import org.graph4j.generate.RandomGnmGraphGenerator;
import org.graph4j.generate.RandomGnpGraphGenerator;

/**
 *
 * @author Cristian Frăsinaru
 */
class BidirectionalDijkstraDemo extends PerformanceDemo {

    private final double probability = 0.1;
    private final int v;
    private final int u;

    public BidirectionalDijkstraDemo() {
        numVertices = 1_000_000;
        runJGraphT = true;
        runOther = true;
        //
        v = 0; //random.nextInt(numVertices);
        u = numVertices - 1; //random.nextInt(numVertices);
    }

    @Override
    protected void createGraph() {
        //graph = new RandomGnpGraphGenerator(numVertices, probability).createDigraph();
        //graph = new RandomGnmGraphGenerator(numVertices, 20 * numVertices).createConnectedGraph();
        //graph = new RandomGnmGraphGenerator(numVertices, 10 * numVertices).createDigraph();
        //graph = GraphGenerator.randomTree(numVertices);
        graph = GraphGenerator.grid(10, numVertices);
        //EdgeWeightsGenerator.randomDoubles(graph, 0, 1);
    }

    @Override
    protected void testGraph4J() {
        var alg1 = new org.graph4j.alg.sp.BidirectionalDijkstra(graph, v, u);
        alg1.findPath();
        System.out.println(alg1.getPathWeight());
        /*
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                var alg = new org.graph4j.alg.sp.BidirectionalDijkstra(graph, i, j);
                alg.findPath();
            }
        }*/
    }

    @Override
    protected void testJGraphT() {
        var alg1 = new org.jgrapht.alg.shortestpath.BidirectionalDijkstraShortestPath(jgrapht);
        alg1.getPath(v, u);
        System.out.println(alg1.getPathWeight(v, u));
        /*
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                var alg = new org.jgrapht.alg.shortestpath.BidirectionalDijkstraShortestPath(jgrapht);
                alg.getPath(i, j);
            }
        }*/
    }

    @Override
    protected void testOther() {
        var alg1 = new org.graph4j.alg.sp.BFSSinglePairShortestPath(graph, v, u);
        alg1.findPath();
        System.out.println(alg1.getPathWeight());
        /*
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                var alg = new org.graph4j.alg.sp.BFSSinglePairShortestPath(graph, i, j);
                alg.findPath();
            }
        }*/
    }

    @Override
    protected void prepareArgs() {
        int steps = 10;
        args = new int[steps];
        for (int i = 0; i < steps; i++) {
            args[i] = 100 * (i + 1);
        }
    }

    public static void main(String args[]) {
        new BidirectionalDijkstraDemo().demo();
    }

}

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
import org.graph4j.metrics.*;
import org.jgrapht.alg.shortestpath.GraphMeasurer;

/**
 *
 * @author Cristian Frăsinaru
 */
class GraphMetricsDemo extends PerformanceDemo {

    private final double probability = 0.1;

    public GraphMetricsDemo() {
        numVertices = 2000;
        runJGraphT = true; //x20
        //runOther = true;
    }

    @Override
    protected void createGraph() {
        graph = GraphGenerator.randomGnp(numVertices, probability);
        //graph = GraphGenerator.randomTree(numVertices);
        //graph = GraphGenerator.cycle(numVertices);
        EdgeWeightsGenerator.randomDoubles(graph, 0, 1);
    }

    @Override
    protected void testGraph4J() {
        var alg = new org.graph4j.metrics.GraphMetrics(graph);
        System.out.println(alg.diameter());
        System.out.println(alg.radius());
        //new org.graph4j.metrics.GraphMetrics(graph).eccentricities();

    }

    @Override
    protected void testJGraphT() {
        var alg = new GraphMeasurer(jgrapht);
        System.out.println(alg.getDiameter());   
        System.out.println(alg.getRadius());
        //alg.getVertexEccentricityMap();
    }

    @Override
    protected void testOther() {
        System.out.println(new ParallelExtremaCalculator(graph).getDiameter());
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
        new GraphMetricsDemo().demo();
    }

}

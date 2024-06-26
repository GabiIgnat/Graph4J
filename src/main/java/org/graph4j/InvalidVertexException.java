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
package org.graph4j;

/**
 *
 * @author Cristian Frăsinaru
 */
public class InvalidVertexException extends RuntimeException {

    /**
     *
     * @param v a vertex number
     */
    public InvalidVertexException(int v) {
        super("Invalid vertex number: " + v);
    }

    /**
     *
     * @param vLabel a vertex label
     */
    public InvalidVertexException(Object vLabel) {
        super("Invalid vertex label: " + vLabel);
    }

    /**
     *
     * @param v a vertex number
     * @param msg a message
     */
    public InvalidVertexException(int v, String msg) {
        super(msg + ": " + v);
    }

}

/**
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.driver.v1;

import org.neo4j.driver.v1.internal.types.TypeConstructor;
import org.neo4j.driver.v1.internal.types.TypeRepresentation;
import org.neo4j.driver.v1.internal.value.ValueAdapter;

import static org.neo4j.driver.v1.internal.types.TypeConstructor.*;

/**
 * Utility class for determining and working with the Cypher types of values
 *
 * @see Value
 * @see CypherType
 * @see CoarseCypherType
 */
public final class CypherTypes
{
    private CypherTypes() {
        throw new UnsupportedOperationException(  );
    }

    /**
     * Determine the {@link CoarseCypherType} of a given {@link Value}
     *
     * @param value the value
     * @return the smallest coarse Cypher type of the value
     */
    public static CoarseCypherType typeOf( Value value )
    {
        if ( value == null )
        {
            return NULL;
        }

        TypeConstructor constructor = ((ValueAdapter) value).typeConstructor();
        switch ( constructor )
        {
        case ANY_TyCon:
            return ANY;
        case BOOLEAN_TyCon:
            return BOOLEAN;
        case STRING_TyCon:
            return STRING;
        case NUMBER_TyCon:
            return NUMBER;
        case INTEGER_TyCon:
            return INTEGER;
        case FLOAT_TyCon:
            return FLOAT;
        case LIST_TyCon:
            return LIST;
        case MAP_TyCon:
            return MAP;
        case IDENTITY_TyCon:
            return IDENTITY;
        case NODE_TyCon:
            return NODE;
        case RELATIONSHIP_TyCon:
            return RELATIONSHIP;
        case PATH_TyCon:
            return PATH;
        case NULL_TyCon:
            return NULL;
        default:
            throw new UnsupportedOperationException( "Unsupported Cypher type" );
        }
    }

    /** the Cypher type ANY */
    public static CoarseCypherType ANY = constructType( ANY_TyCon );

    /** the Cypher type BOOLEAN */
    public static CoarseCypherType BOOLEAN = constructType( BOOLEAN_TyCon );

    /** the Cypher type STRING */
    public static CoarseCypherType STRING = constructType( STRING_TyCon );

    /** the Cypher type NUMBER */
    public static CoarseCypherType NUMBER = constructType( NUMBER_TyCon );

    /** the Cypher type INTEGER */
    public static CoarseCypherType INTEGER = constructType( INTEGER_TyCon );

    /** the Cypher type FLOAT */
    public static CoarseCypherType FLOAT = constructType( FLOAT_TyCon );

    /** the Cypher type LIST */
    public static CoarseCypherType LIST = constructType( LIST_TyCon );

    /** the Cypher type MAP */
    public static CoarseCypherType MAP = constructType( MAP_TyCon );

    /** the Cypher type IDENTITY */
    public static CoarseCypherType IDENTITY = constructType( IDENTITY_TyCon );

    /** the Cypher type NODE */
    public static CoarseCypherType NODE = constructType( NODE_TyCon );

    /** the Cypher type RELATIONSHIP */
    public static CoarseCypherType RELATIONSHIP = constructType( RELATIONSHIP_TyCon );

    /** the Cypher type PATH */
    public static CoarseCypherType PATH = constructType( PATH_TyCon );

    /** the Cypher type NULL */
    public static CoarseCypherType NULL = constructType( NULL_TyCon );

    private static TypeRepresentation constructType( TypeConstructor tyCon )
    {
        return new TypeRepresentation( tyCon );
    }
}

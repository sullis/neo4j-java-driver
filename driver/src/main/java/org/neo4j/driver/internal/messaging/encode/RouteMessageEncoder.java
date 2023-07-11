/*
 * Copyright (c) "Neo4j"
 * Neo4j Sweden AB [http://neo4j.com]
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
package org.neo4j.driver.internal.messaging.encode;

import static org.neo4j.driver.Values.value;
import static org.neo4j.driver.internal.util.Preconditions.checkArgument;

import java.io.IOException;
import org.neo4j.driver.Bookmark;
import org.neo4j.driver.internal.messaging.Message;
import org.neo4j.driver.internal.messaging.MessageEncoder;
import org.neo4j.driver.internal.messaging.ValuePacker;
import org.neo4j.driver.internal.messaging.request.RouteMessage;

/**
 * Encodes the ROUTE message to the stream
 */
public class RouteMessageEncoder implements MessageEncoder {
    @Override
    public void encode(Message message, ValuePacker packer) throws IOException {
        checkArgument(message, RouteMessage.class);
        var routeMessage = (RouteMessage) message;
        packer.packStructHeader(3, message.signature());
        packer.pack(routeMessage.routingContext());
        packer.pack(value(routeMessage.bookmarks().stream().map(Bookmark::value)));
        packer.pack(routeMessage.databaseName());
    }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ai.platon.pulsar.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

/**
 * Utility methods for common filesystem operations.
 *
 * @author vincent
 * @version $Id: $Id
 */
public class LocalFSUtils {


    public static final Logger LOG = LoggerFactory.getLogger(LocalFSUtils.class);

    /**
     * Read all lines in file, if it's under HDFS, read from HDFS, or read from local file system
     *
     * @param path a {@link java.lang.String} object.
     * @return a {@link java.util.List} object.
     * @throws java.io.IOException if any.
     */
    public static List<String> readAllLines(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    /**
     * <p>readAllLinesSilent.</p>
     *
     * @param path a {@link java.nio.file.Path} object.
     * @return a {@link java.util.List} object.
     */
    public static List<String> readAllLinesSilent(Path path) {
        try {
            if (Files.exists(path)) {
                return Files.readAllLines(path);
            } else {
                Files.createDirectories(path.getParent());
                Files.write(path, "".getBytes(), StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            LOG.warn(e.toString());
        }

        return Collections.emptyList();
    }
}

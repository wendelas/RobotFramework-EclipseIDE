/**
 * Copyright 2012 Nitor Creations Oy
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
package com.nitorcreations.robotframework.eclipseide.internal.hyperlinks;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.hyperlink.IHyperlink;

import com.nitorcreations.robotframework.eclipseide.builder.parser.RFELine;
import com.nitorcreations.robotframework.eclipseide.structure.ParsedString;
import com.nitorcreations.robotframework.eclipseide.structure.ParsedString.ArgumentType;

/**
 * This hyperlink detector creates hyperlinks for resource references, e.g.
 * "Resource foo.txt" --> "foo.txt" is linked.
 * 
 * @author xkr47
 */
public class ResourceHyperlinkDetector extends HyperlinkDetector {

    @Override
    protected IHyperlink[] getLinks(IDocument document, RFELine rfeLine, ParsedString argument, int offset) {
        ParsedString firstArgument = rfeLine.arguments.get(0);
        if (firstArgument.getType() != ArgumentType.SETTING_KEY) {
            return null;
        }
        if (!firstArgument.getValue().equals("Resource")) {
            return null;
        }
        ParsedString resourceName = rfeLine.arguments.get(1);
        // TODO: jump to resource
        return getLinks(document, resourceName, null);
    }

}
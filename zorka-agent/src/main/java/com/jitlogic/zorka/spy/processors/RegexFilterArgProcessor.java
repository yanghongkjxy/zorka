/**
 * Copyright 2012 Rafal Lewczuk <rafal.lewczuk@jitlogic.com>
 * <p/>
 * This is free software. You can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * <p/>
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this software. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jitlogic.zorka.spy.processors;

import com.jitlogic.zorka.spy.SpyRecord;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Filters records using regular expressions.
 */
public class RegexFilterArgProcessor implements SpyArgProcessor {

    private final int src;
    private final Pattern regex;
    private final boolean filterOut;


    public RegexFilterArgProcessor(int src, String regex) {
        this(src, regex, false);
    }


    public RegexFilterArgProcessor(int src, String regex, boolean filterOut) {
        this.src = src;
        this.regex = Pattern.compile(regex);
        this.filterOut = filterOut;
    }


    public SpyRecord process(int stage, SpyRecord record) {
        Object val = record.get(stage, src);

        boolean matches = val != null && regex.matcher(val.toString()).matches();

        return matches^filterOut ? record : null;
    }
}

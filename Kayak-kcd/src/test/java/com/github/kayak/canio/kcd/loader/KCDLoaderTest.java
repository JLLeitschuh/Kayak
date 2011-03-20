/**
 * 	This file is part of Kayak.
 *
 *	Kayak is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU Lesser General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	Kayak is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public License
 *	along with Kayak.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.github.kayak.canio.kcd.loader;

import java.util.HashSet;
import com.github.kayak.core.description.BusDescription;
import com.github.kayak.core.description.Document;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan-Niklas Meier < dschanoeh@googlemail.com >
 */
public class KCDLoaderTest {
    private static String sample = "src/test/resources/can_definition_sample.kcd";
    private static KCDLoader loader = new KCDLoader();
    private static Document document;


    public KCDLoaderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        document = loader.parseFile(new File(sample));
        assertNotNull(document);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of parseFile method, of class KCDLoader.
     */
    @Test
    public void testParseFile() {
        System.out.println("parseFile");

        System.out.println("Document properties");
        assertEquals("The Homer", document.getName());
        assertEquals("1.23", document.getVersion());
        assertEquals("Herbert Powell", document.getAuthor());
        assertEquals("Powell Motors", document.getCompany());
        
        System.out.println("nodes");
        HashSet<String> nodes = document.getNodes();
        assertTrue(nodes.contains("Motor ACME"));
        assertTrue(nodes.contains("Crypto"));
        assertTrue(nodes.contains("ParkDistance"));


        HashSet<BusDescription> busses = document.getBusses();
        assertEquals(3, busses.size());
        
    }

    /**
     * Test of getSupportedExtensions method, of class KCDLoader.
     */
    @Test
    public void testGetSupportedExtensions() {
        System.out.println("getSupportedExtensions");
        
        String[] expResult = new String[]{"kcd", "kcd.gz"};
        String[] result = loader.getSupportedExtensions();
        assertEquals(2, result.length);
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
    }

}
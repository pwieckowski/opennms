/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2007-2012 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2012 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.web.map;

/*
 * Created on 8-giu-2005
 *
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opennms.web.map.view.Manager;
import org.opennms.web.map.view.VElement;
import org.opennms.web.map.view.VMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>AddMapsController class.</p>
 *
 * @author mmigliore
 *
 * this class provides to create, manage and delete
 * proper session objects to use when working with maps
 * @version $Id: $
 * @since 1.8.1
 */
public class AddMapsController extends MapsLoggingController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AddMapsController.class);


	private Manager manager;
	
	
	/**
	 * <p>Getter for the field <code>manager</code>.</p>
	 *
	 * @return a {@link org.opennms.web.map.view.Manager} object.
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * <p>Setter for the field <code>manager</code>.</p>
	 *
	 * @param manager a {@link org.opennms.web.map.view.Manager} object.
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
	    String elems = request.getParameter("elems");
	    LOG.debug("Adding Maps: elems={}", elems );

	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
	    try {
	        VMap map = manager.openMap();
	        List<VElement> velems = new ArrayList<VElement>();
	        // response for addElement
	        List<Integer> mapsWithLoop = new ArrayList<Integer>();
	        LOG.debug("Got map from manager {}", map);

	        LOG.debug("Adding maps by id: {}", elems);
	        String[] smapids = elems.split(",");

	        for (int i = 0; i<smapids.length;i++) {
	            Integer id = Integer.valueOf(smapids[i]);
	            if (map.containsElement(id, MapsConstants.MAP_TYPE)) {
	                LOG.debug(" Map Contains Element: {}", id+MapsConstants.MAP_TYPE);
	                continue;

	            }
	            boolean foundLoop = manager.foundLoopOnMaps(map,id);

	            if(foundLoop) {
	                mapsWithLoop.add(id);
	            } else {
	                velems.add(manager.newElement(map.getId(),id, MapsConstants.MAP_TYPE));
	            }
	        } // end for

	        //get map
	        map = manager.addElements(map, velems);
	        bw.write(ResponseAssembler.getAddElementResponse(mapsWithLoop,velems,map.getLinks()));
	    } catch (Throwable e) {
	        LOG.error("Error while adding Maps: ", e);
	        bw.write(ResponseAssembler.getMapErrorResponse(MapsConstants.ADDMAPS_ACTION));
	    } finally {
	        bw.close();
	    }

	    return null;
	}

}

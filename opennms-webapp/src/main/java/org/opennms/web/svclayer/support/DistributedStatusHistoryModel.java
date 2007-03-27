//
// This file is part of the OpenNMS(R) Application.
//
// OpenNMS(R) is Copyright (C) 2006 The OpenNMS Group, Inc.  All rights reserved.
// OpenNMS(R) is a derivative work, containing both original code, included code and modified
// code that was published under the GNU General Public License. Copyrights for modified
// and included code are below.
//
// OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
//
// Original code base Copyright (C) 1999-2001 Oculan Corp.  All rights reserved.
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
//
// For more information contact:
//      OpenNMS Licensing       <license@opennms.org>
//      http://www.opennms.org/
//      http://www.opennms.com/
//
package org.opennms.web.svclayer.support;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.opennms.netmgt.model.OnmsApplication;
import org.opennms.netmgt.model.OnmsLocationMonitor;
import org.opennms.netmgt.model.OnmsMonitoredService;
import org.opennms.netmgt.model.OnmsMonitoringLocationDefinition;
import org.opennms.web.graph.RelativeTimePeriod;

public class DistributedStatusHistoryModel {
    private List<OnmsMonitoringLocationDefinition> m_locations;
    private List<OnmsApplication> m_applications;
    private OnmsMonitoringLocationDefinition m_chosenLocation;
    private OnmsApplication m_chosenApplication;
    private Collection<OnmsMonitoredService> m_chosenApplicationMemberServices;
    private List<String> m_errors;
    private List<RelativeTimePeriod> m_periods;
    private RelativeTimePeriod m_chosenPeriod;
    private List<OnmsLocationMonitor> m_monitors;
    private OnmsLocationMonitor m_chosenMonitor;
    private Map<OnmsMonitoredService, String> m_httpGraphUrls;
    
    public DistributedStatusHistoryModel(
            List<OnmsMonitoringLocationDefinition> locations,
            List<OnmsApplication> applications,
            List<OnmsLocationMonitor> monitors,
            List<RelativeTimePeriod> periods,
            OnmsMonitoringLocationDefinition chosenLocation,
            OnmsApplication chosenApplication,
            Collection<OnmsMonitoredService> chosenApplicationMemberServices,
            OnmsLocationMonitor chosenMonitor,
            RelativeTimePeriod chosenPeriod,
            List<String> errors) {
        m_locations = locations;
        m_applications = applications;
        m_monitors = monitors;
        m_periods = periods;
        m_chosenLocation = chosenLocation;
        m_chosenApplication = chosenApplication;
        m_chosenApplicationMemberServices = chosenApplicationMemberServices;
        m_chosenMonitor = chosenMonitor;
        m_chosenPeriod = chosenPeriod;
        m_errors = errors;
        
    }

    public List<OnmsApplication> getApplications() {
        return m_applications;
    }

    public List<OnmsMonitoringLocationDefinition> getLocations() {
        return m_locations;
    }

    public OnmsApplication getChosenApplication() {
        return m_chosenApplication;
    }

    public Collection<OnmsMonitoredService> getChosenApplicationMemberServices() {
        return m_chosenApplicationMemberServices;
    }

    public OnmsMonitoringLocationDefinition getChosenLocation() {
        return m_chosenLocation;
    }
    
    public List<String> getErrors() {
        return m_errors;
    }

    public RelativeTimePeriod getChosenPeriod() {
        return m_chosenPeriod;
    }

    public List<RelativeTimePeriod> getPeriods() {
        return m_periods;
    }

    public OnmsLocationMonitor getChosenMonitor() {
        return m_chosenMonitor;
    }

    public List<OnmsLocationMonitor> getMonitors() {
        return m_monitors;
    }
    
    public Map<OnmsMonitoredService, String> getHttpGraphUrls() {
        return m_httpGraphUrls;
    }
    
    public void setHttpGraphUrls(Map<OnmsMonitoredService, String> list) {
        m_httpGraphUrls = list;
    }
    
}

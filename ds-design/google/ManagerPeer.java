public class ManagerPeer {
    Map<String, Employee> employeeMap = new HashMap<> ();

    // ea is the manager
    public void setManager(String ea, String eb) {
        if(!employeeMap.containsKey(ea)) employeeMap.put(ea, new Employee(ea));
        if(!employeeMap.containsKey(eb)) employeeMap.put(eb, new Employee(eb));

        employeeMap.get(eb).manager = employeeMap.get(ea);
    }

    public void setPeer(String ea, String eb) {
        if(!employeeMap.containsKey(ea)) employeeMap.put(ea, new Employee(ea));
        if(!employeeMap.containsKey(eb)) employeeMap.put(eb, new Employee(eb));

        employeeMap.get(ea).peers.add(employeeMap.get(eb));
        employeeMap.get(eb).peers.add(employeeMap.get(ea));
    }

    public boolean isManaging(String ea, String eb) {
        if(!employeeMap.containsKey(ea) || !employeeMap.containsKey(eb)) return false;

        Employee EB = employeeMap.get(eb);
        while(EB != null) {
            if(EB.manager.equals(ea)) {
                return true;
            }
            EB = EB.manager;
        }
        return false;
    }
}

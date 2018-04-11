public class PartyInvitation {

    public int getMaxLevelSum(Employee ceo) {
        if(root == null) return MaxLevelSum(0, 0);
        if(root.reports.isEmpty()) return MaxLevelSum(root.level, 0);

        int inviteLevel = 0;
        int notInviteLevel = 0;

        for(Employee e: ceo.reports) {
            MaxLevelSum child = getMaxLevelSum(e);
            inviteLevel += child.notInvite;
            notInviteLevel += child.notInvite + child.notInvite;
        }

        return new MaxLevelSum(inviteLevel, notInviteLevel);
    }

    class Employee {
        String name;
        int level;
        List<Employee> reports;
    }

    class MaxLevelSum {
        int invite;
        int notInvite;
    }
}

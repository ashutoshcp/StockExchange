package phonepe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static void main(String[] args) {
        new Solution().process();
    }

    private void process() {
        Branch branch = new Branch();
        VehicleAllocation allocation = new VehicleAllocation();
        //addBranch(“Vasanth Vihar”)
        //addBranch(“Cyber City”)
        branch.addBranchName("Vasanth Vihar");
        branch.addBranchName("Cyber City");
        allocation.allocatePrice("Vasanth Vihar", VehicleType.Sedan, 100);
    }

    public static class Branch {
        private static final Map<String, Integer>       branchNamesIdMap   = new HashMap<>();
        private static final Map<Integer, BranchDetail> branchIdDetailsMap = new HashMap<>();
        private final        AtomicInteger              counter            = new AtomicInteger();

        public void addBranchName(String branch) {
            if (branchNamesIdMap.get(branch) == null) {
                BranchDetail branchDetail = new BranchDetail(branch);
                int i = counter.incrementAndGet();
                branchNamesIdMap.put(branch, i);
                branchIdDetailsMap.put(i,branchDetail);
                System.out.println("Branch: " + branchDetail.getName() + " id: " + i);
            }
        }

        public static Integer getBranchId(String branch) {
            return branchNamesIdMap.get(branch);
        }

        public static BranchDetail getBranch(Integer id) {
            return branchIdDetailsMap.get(id);
        }
    }

    public static class BranchDetail {
        private final String name;
        private final String city;

        public BranchDetail(String name) {
            this.name = name;
            this.city = "DELHI";
        }

        public BranchDetail(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            BranchDetail that = (BranchDetail) o;
            return Objects.equals(name, that.name) && Objects.equals(city, that.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, city);
        }

        @Override public String toString() {
            return "BranchDetail{" + "name='" + name + '\'' + ", city='" + city + '\'' + '}';
        }
    }

    public static class VehicleAllocation {
        private final Map<Integer, List<Vehicle>> branchAvailableVehicles = new HashMap<>();

        public void allocatePrice(String branch, VehicleType vehicleType, int price) {
            Integer branchId = Branch.getBranchId(branch);

        }

        
    }

    public  static class Vehicle {
        private VehicleType vehicleType;
        private Integer branchId;
        private double ratePerHour;
        private Status status;

        public Vehicle() {
        }

        public VehicleType getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
        }



        public double getRatePerHour() {
            return ratePerHour;
        }

        public void setRatePerHour(double ratePerHour) {
            this.ratePerHour = ratePerHour;
        }
    }

    public enum VehicleType {
        Sedan,
        Hatchback,
        SUV
    }

    public enum Status {
        Available,
        Booked
    }
}

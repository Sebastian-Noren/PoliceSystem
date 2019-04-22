package pust.model.entity;

public abstract class Person {

    public static class Builder {
        private String firstName;
        private String surname;
        private PersonalNumber personalNumber;
        private Address address;
        private Record<Crime> crimeRecord;
        private int height;
        private Identification identification;

        public Builder() {

        }

        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder withSurname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder withPersonalNumber(PersonalNumber personalNumber){
            this.personalNumber = personalNumber;
            return this;
        }

        public Builder withAddress(Address address){
            this.address = address;
            return this;
        }

        public Builder withCrimeRecord(Record<Crime> crimeRecord){
            this.crimeRecord = crimeRecord;
            return this;
        }

        public Builder withHeight(int height){
            this.height = height;
            return this;
        }

        public Builder withIdentification(Identification identification){
            this.identification = identification;
            return this;
        }

    }

    private Person(){
        //Default constructor
    }

}

package builder;

public class Contact {
    private String name;
    private String nickname;
    private String phoneNumber;
    private String company;
    private String email;
    private String birthday;

    public Contact() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Contact contact;

        private Builder() {
            this.contact = new Contact();
        }

        public Builder withName(String name) {
            this.contact.name = name;

            return this;
        }

        public Builder withNickname(String nickname) {
            this.contact.nickname = nickname;

            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.contact.phoneNumber = phoneNumber;

            return this;
        }

        public Builder withCompany(String company) {
            this.contact.company = company;

            return this;
        }

        public Builder withEmail(String email) {
            this.contact.email = email;

            return this;
        }

        public Builder withBirthday(String birthday) {
            this.contact.birthday = birthday;

            return this;
        }

        public Contact build() {
            return this.contact;
        }
    }
}

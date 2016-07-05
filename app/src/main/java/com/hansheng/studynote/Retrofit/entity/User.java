package com.hansheng.studynote.Retrofit.entity;

import java.util.List;

/**
 * Created by hansheng on 2016/7/5.
 */
public class User {


    private boolean has_more;
    private int quota_max;
    private int quota_remaining;


    private List<ItemsBean> items;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(int quota_max) {
        this.quota_max = quota_max;
    }

    public int getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(int quota_remaining) {
        this.quota_remaining = quota_remaining;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * bronze : 7320
         * silver : 6321
         * gold : 468
         */

        private BadgeCountsBean badge_counts;
        private int account_id;
        private boolean is_employee;
        private int last_modified_date;
        private int last_access_date;
        private int age;
        private int reputation_change_year;
        private int reputation_change_quarter;
        private int reputation_change_month;
        private int reputation_change_week;
        private int reputation_change_day;
        private int reputation;
        private int creation_date;
        private String user_type;
        private int user_id;
        private int accept_rate;
        private String location;
        private String website_url;
        private String link;
        private String profile_image;
        private String display_name;

        public BadgeCountsBean getBadge_counts() {
            return badge_counts;
        }

        public void setBadge_counts(BadgeCountsBean badge_counts) {
            this.badge_counts = badge_counts;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public boolean isIs_employee() {
            return is_employee;
        }

        public void setIs_employee(boolean is_employee) {
            this.is_employee = is_employee;
        }

        public int getLast_modified_date() {
            return last_modified_date;
        }

        public void setLast_modified_date(int last_modified_date) {
            this.last_modified_date = last_modified_date;
        }

        public int getLast_access_date() {
            return last_access_date;
        }

        public void setLast_access_date(int last_access_date) {
            this.last_access_date = last_access_date;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getReputation_change_year() {
            return reputation_change_year;
        }

        public void setReputation_change_year(int reputation_change_year) {
            this.reputation_change_year = reputation_change_year;
        }

        public int getReputation_change_quarter() {
            return reputation_change_quarter;
        }

        public void setReputation_change_quarter(int reputation_change_quarter) {
            this.reputation_change_quarter = reputation_change_quarter;
        }

        public int getReputation_change_month() {
            return reputation_change_month;
        }

        public void setReputation_change_month(int reputation_change_month) {
            this.reputation_change_month = reputation_change_month;
        }

        public int getReputation_change_week() {
            return reputation_change_week;
        }

        public void setReputation_change_week(int reputation_change_week) {
            this.reputation_change_week = reputation_change_week;
        }

        public int getReputation_change_day() {
            return reputation_change_day;
        }

        public void setReputation_change_day(int reputation_change_day) {
            this.reputation_change_day = reputation_change_day;
        }

        public int getReputation() {
            return reputation;
        }

        public void setReputation(int reputation) {
            this.reputation = reputation;
        }

        public int getCreation_date() {
            return creation_date;
        }

        public void setCreation_date(int creation_date) {
            this.creation_date = creation_date;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getAccept_rate() {
            return accept_rate;
        }

        public void setAccept_rate(int accept_rate) {
            this.accept_rate = accept_rate;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getWebsite_url() {
            return website_url;
        }

        public void setWebsite_url(String website_url) {
            this.website_url = website_url;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public static class BadgeCountsBean {
            private int bronze;
            private int silver;
            private int gold;

            public int getBronze() {
                return bronze;
            }

            public void setBronze(int bronze) {
                this.bronze = bronze;
            }

            public int getSilver() {
                return silver;
            }

            public void setSilver(int silver) {
                this.silver = silver;
            }

            public int getGold() {
                return gold;
            }

            public void setGold(int gold) {
                this.gold = gold;
            }
        }
    }
}

package taxi_enums;

/**
 * Created by Marina on 15.05.2017.
 */
public enum LargeAuto {
    MICROBUS,
    PICKUP,
    TRUCK { //anonymous class

        public double computeDeliveryPrice() {
            return (this.getSeats() * this.getSeatPrice() + this.getVolume() * this.getVolumePrice()) * 1.05;
        }
    };
        protected int seats;
        protected double volume;
        private double seatPrice;
        private double volumePrice;

        public int getSeats()
        {
            return seats;
        }
        public double getVolume()
        {
            return volume;
        }
        public double getSeatPrice()
        {
            return seatPrice;
        }
        public double getVolumePrice()
        {
            return volumePrice;
        }
        public void setParams(int seats, double volume)
        {
            this.seats = seats;
            this.volume = volume;
        }
        public void setPrices (double seatPrice, double volumePrice)
        {
            this.seatPrice = seatPrice;
            this.volumePrice = volumePrice;
        }
        public double computeDeliveryPrice()
        {
            return (this.getSeats()*this.getSeatPrice()+this.getVolume()*this.getVolumePrice());
        }
    @Override
    public String toString(){
        return String.format("%s : seats = %d, volume = %f, seatPrice = %f, volumePrice = %f, DeliveryPrice = %f",
                            name(),seats,volume,seatPrice,volumePrice,computeDeliveryPrice());
    }
    }


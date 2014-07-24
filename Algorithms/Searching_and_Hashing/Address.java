public class Address
{
    private String address;

    public Address(String anAddress)
    {
        address = anAddress;
    }

    public final String getAddress()
    {
        return address;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        int n = address.length();
        final int g = 31;
        for(int i = 0; i < n; i++)
            hash = g * hash + address.charAt(i);

        return hash;

        // alternatively can just return address.hashCode()
    }
}
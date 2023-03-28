package org.example;
import java.util.Random;
public class CustomerBaseInfo {
    private String[] prefixArray = {"0011", "0012", "0016", "0017"};
    Random random = new Random();
    private String prefix = prefixArray[random.nextInt(prefixArray.length)];

    public String GenerateIDGenerator() {
        String suffix = "";
        for (int i = 0; i < 8; i++) {
            suffix += random.nextInt(10);
        }
        return prefix + suffix;
    }

    public String GenerateTypeGenerator() {

            if( prefix.equals("0011") || prefix.equals("0012") )
            {
                return "I";
            }
             else {
                return "C";
            }
    }

    public String RandomCustomerNameGenerator()  {

        if( prefix.equals("0011") || prefix.equals("0012") )
        {
            return new RandomCustomerNameGenerator().PerCustomerNameGenerator();
        }
        else {

            return new RandomCustomerNameGenerator().CorCustomerNameGenerator();
        }

    }

    public String GeneratePhoneNumber() {

        if( prefix.equals("0011") || prefix.equals("0012") )
        {
            return new GeneratePhoneNumber().GeneratePerNumber();
        }
        else
        {
            return new GeneratePhoneNumber().GenerateCorNumber();
        }

    }

    public String RandomCustomerIdGenerator()  {

        if( prefix.equals("0011") || prefix.equals("0012") )
        {
            return new IdNumberGenerator().generateIdNumber();
        }
        else {

            return new IdNumberGenerator().OrganizationCodeGenerator();
        }

    }

}

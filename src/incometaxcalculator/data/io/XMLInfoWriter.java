package incometaxcalculator.data.io;

public class XMLInfoWriter extends InfoWriter {
  private static final String[] TAXPAYER_PREFIXES = 
    {"<Name> ", "<AFM> ", "<Status> ", "<Income> ", "<Receipts>"};
  private static final String[] TAXPAYER_SUFFIXES = 
    {" </Name> ", " </AFM> ", " </Status> ", " </Income> ", " </Receipts>"};
  private static final String[] RECEIPT_PREFIXES = 
    {"<ReceiptID> ", "<Date> ", "<Kind> ", "<Amount> " , "<Company> ",
        "<Country> ", "<City> ", "<Street> " ,"<Number> "};
  private static final String[] RECEIPT_SUFFIXES = 
    {" </ReceiptID> ", " </Date> ", " </Kind> ", " </Amount> " , " </Company> ", 
        " </Country> ", " </City> ", " </Street> " ," </Number> "};


  public XMLInfoWriter() {
    super(TAXPAYER_PREFIXES, TAXPAYER_SUFFIXES, RECEIPT_PREFIXES, 
          RECEIPT_SUFFIXES);
  }

}
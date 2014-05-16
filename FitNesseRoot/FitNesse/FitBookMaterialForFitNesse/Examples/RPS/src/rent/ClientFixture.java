package rent;

import fitlibrary.DoFixture;

public class ClientFixture extends DoFixture {
    private prs.Client client;

    public ClientFixture(prs.Client client) {
        this.client = client;
    }
	public boolean accountOwingIs(Money accountOwing) {
		return client.getAmountOwing().equals(accountOwing);
	}
}

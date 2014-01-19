import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sdp.MediaDescription;
import javax.sdp.SdpException;
import javax.sdp.SdpFactory;
import javax.sdp.SdpParseException;
import javax.sdp.SessionDescription;
import javax.swing.SwingUtilities;
import net.mc_cubed.icedjava.ice.event.IceEvent;
import net.mc_cubed.icedjava.ice.event.IceEventListener;


public class main {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SdpFactory factory = SdpFactory.getInstance();
        IceSocket[] iceSockets = new IceSocket[]{
            IceFactory.createIceSocket(factory.createMediaDescription("video", 0, 2, "RTP/AVP", new String[]{"26"}).getMedia()),
            IceFactory.createIceSocket(factory.createMediaDescription("audio", 0, 2, "RTP/AVP", new String[]{"8"}).getMedia())};
        IcePeerImpl instance = (IcePeerImpl) IceFactory.createIcePeer("localPeer", iceSockets);
        List<LocalCandidate> candidates = new LinkedList<LocalCandidate>();
        for (IceSocket socket : instance.getIceSockets()) {
            candidates.addAll(instance.getLocalCandidates(socket));
        }
        for (LocalCandidate candidate : candidates) {
            System.out.println(candidate);
        }

        instance.close();

        for (IceSocket socket : iceSockets) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(IcePeerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

		
		
//		Media media = SdpFactory.getInstance().createMediaDescription("video", 0, 2, "RTP/AVP", new String[]{"26"}).getMedia();		
		
		
//		IceSocket socket = IceFactory.createIceSocket(media);		
		
//		System.out.println("Hello World");
	}	



}


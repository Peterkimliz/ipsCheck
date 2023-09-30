// package com.ips.ipsManager.Services;

// public class Calculator {

//     private void updateResult(String ipAddress) {
//         try {
           
//             NetworkManager.Subnet mIpNetwork = new NetworkManager.Subnet(ipAddress);
//             int mask = Integer.valueOf(mIpMaskEditText.getText().toString());

//             String networkAddress = mIpNetwork.address;
//             String networkRange = mIpNetwork.range;
//             String networkBroadcast = mIpNetwork.broadcast;
//             String networkAllocatedSize = String.valueOf(mIpNetwork.allocatedSize);
//             String wildcardMask = NetworkManager.toDecWildCardMask(mask);
//             if (mask == 0) {
//                 networkAddress = "0.0.0.0";
//                 networkRange = "0.0.0.0 - 255.255.255.254";
//                 networkBroadcast = "255.255.255.255";

//             } else if (mask > 30) {
//                 networkRange = "NONE";
//                 networkAllocatedSize = "NONE";
//                 networkBroadcast = "NONE";
//             }


//             mResultsTableLayout.setVisibility(View.VISIBLE);
//             //** hide Keyboard ?

//         }catch(Exception e){

//         }

//     }
    
// }

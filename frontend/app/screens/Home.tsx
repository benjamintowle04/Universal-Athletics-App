import { View, Text, Button, Image, Alert} from 'react-native'
import React, {useContext} from 'react'
import { FIREBASE_AUTH } from '../../firebase_config';
import { RouterProps } from '../types/RouterProps';
import { UserContext } from '../contexts/UserContext';
import { BottomNavBar } from '../components/navigation/BottomNavBar';


const Home = ({ navigation }: RouterProps) => {    
  const auth = FIREBASE_AUTH;
  const userContext = useContext(UserContext);

  if (!userContext) {
    Alert.alert("Error loading User Data");
    return null;
  }

  const { userData, setUserData } = userContext;
  return (
    <View>
      <Text>{userData.firstName}</Text>
      <Text>{userData.lastName}</Text>
      <Text>{userData.email}</Text>
      <Text>{userData.phoneNumber}</Text>
      <Text>{userData.bio}</Text>


      <Image
        source={require('../images/praiseDaLord.jpg')}
        className="h-full w-full"
        resizeMode="contain"
      />   

       
    </View>
  )
}

export default Home

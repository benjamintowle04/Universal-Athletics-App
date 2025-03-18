import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { useState, useEffect } from 'react';
import { onAuthStateChanged, User } from 'firebase/auth';
import { FIREBASE_AUTH } from './firebase_config';  
import Login from './app/screens/Login';
import SignUp from './app/screens/SignUp';
import EntryPoint from './app/screens/EntryPoint';
import Home from './app/screens/Home';
import GenInfo from './app/screens/onboarding/GenInfo';
import EnterSkills from './app/screens/onboarding/EnterSkills';
import React from 'react';
import { UserProvider } from './app/contexts/UserContext';
import AccountSummary from './app/screens/onboarding/AccountSummary';
import { Ionicons } from '@expo/vector-icons';
import { Text } from 'react-native';
import UserSettings from './app/screens/UserSettings';
import MyCoaches from './app/screens/MyCoaches';

// Create placeholder screens for the tab navigator
const ScheduleScreen = () => <Text>Schedule Screen</Text>;
const MerchScreen = () => <Text>Merch Screen</Text>;

const PreLoginStack = createNativeStackNavigator();
const PostLoginStack = createNativeStackNavigator();
const Tab = createBottomTabNavigator();

// Main tab navigator that will have the bottom navigation bar
function MainTabNavigator() {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        tabBarIcon: ({ focused, color, size }) => {
          // Use explicit type for iconName with specific Ionicons names
          let iconName: keyof typeof Ionicons.glyphMap = 'home';

          if (route.name === 'HomeTab') {
            iconName = focused ? 'home' : 'home-outline';
          } else if (route.name === 'ScheduleMonthView') {
            iconName = focused ? 'fitness' : 'fitness-outline';
          } else if (route.name === 'Merch') {
            iconName = focused ? 'person' : 'person-outline';
          } else if (route.name === 'MyCoaches') {
            iconName = focused ? 'people-outline' : 'people-outline';
          } else if (route.name === 'Profile') {
            iconName = focused ? 'settings' : 'settings-outline';
          } 
          

          return <Ionicons name={iconName} size={size} color={color} />;
        },
        tabBarActiveTintColor: 'blue',
        tabBarInactiveTintColor: 'gray',
      })}
    >
      <Tab.Screen 
        name="HomeTab" 
        component={Home} 
        options={{ 
          headerShown: true,
          title: 'Home'
        }}
      />
      <Tab.Screen 
        name="ScheduleMonthView" 
        component={ScheduleScreen} 
        options={{ title: 'Schedule' }}
      />
      <Tab.Screen 
        name="Merch" 
        component={MerchScreen} 
        options={{ title: 'Merch' }}
      />

      <Tab.Screen 
        name="MyCoaches"
        component={MyCoaches}
        options={{ title: 'My Coaches'}}
      />

      <Tab.Screen 
        name="Profile" 
        component={UserSettings} 
        options={{ title: 'Settings' }}
      />

      
    </Tab.Navigator>
  );
}

export default function App() {
  const [user, setUser] = useState<User | null >(null);
  const [newUser, setNewUser] = useState(false);
  
  const backButtonOnlyHeader = {
    headerShown: true,
    title: '',
    headerBackTitle: 'Back'
  }

  useEffect(() => {
    const unsubscribe = onAuthStateChanged(FIREBASE_AUTH, (user) => {
      console.log('User', user);
      setUser(user);

      if (user?.metadata.creationTime === user?.metadata.lastSignInTime) {
        setNewUser(true);
      } else {
        setNewUser(false);  
      }
    });

    return () => unsubscribe();
  }, []);

  function PostLoginLayout() {
    if (newUser) {
      return (
        <PostLoginStack.Navigator initialRouteName='GenInfo'>
          <PostLoginStack.Screen 
            name="GenInfo" 
            component={GenInfo} 
            options={backButtonOnlyHeader}
          />

          <PostLoginStack.Screen 
            name="EnterSkills" 
            component={EnterSkills} 
            options={backButtonOnlyHeader}
          />

          <PostLoginStack.Screen 
            name="AccountSummary" 
            component={AccountSummary} 
            options={backButtonOnlyHeader}
          />

          <PostLoginStack.Screen 
            name="Home" 
            component={MainTabNavigator} 
            options={{headerShown: false}}
          />
        </PostLoginStack.Navigator>
      );
    }
    else {
      return <MainTabNavigator />;
    }
  }

  function PreLoginLayout() {
    return (
      <PreLoginStack.Navigator initialRouteName='EntryPoint'>
        <PreLoginStack.Screen 
          name="EntryPoint" 
          component={EntryPoint} 
          options={{ headerShown: false }}
        />
        <PreLoginStack.Screen 
          name="Login" 
          component={Login}
          options={backButtonOnlyHeader}
        />
        <PreLoginStack.Screen 
          name="SignUp" 
          component={SignUp} 
          options={backButtonOnlyHeader}
        />
      </PreLoginStack.Navigator>
    );
  }

  return (
    <UserProvider>
      <NavigationContainer>
        <PostLoginLayout />
        {/* {user ? (   
          <PostLoginLayout />
        ) : (
          <PreLoginLayout/>
        )} */}
      </NavigationContainer>
    </UserProvider>
  );
}

import React from 'react';
import { Text, StyleSheet } from 'react-native';
import { Colors } from '../../themes/colors/Colors';
import { useFonts } from 'expo-font';


interface SubHeaderTextProps {
    text: string;
}

export const SubHeaderText1 = ({ text }: SubHeaderTextProps) => {
    const [fontsLoaded] = useFonts({
        Gagalin: require('../../themes/fonts/Gagalin-Regular.otf'),
      });

    return (
        <Text style={styles.title}>{text}</Text>
    );
};

const styles = StyleSheet.create({
    title: {
        fontSize: 20,
        fontFamily: 'Gagalin',
        color: Colors.uaBlue,
        textAlign: 'center',
        marginBottom: 20,
        textTransform: 'uppercase',
        letterSpacing: 1.5,
    },
    
});

